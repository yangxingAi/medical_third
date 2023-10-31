from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler
import numpy as np
import pandas as pd
import pymysql
import sys
# 建立数据库连接
# 建立数据库连接
connection = pymysql.connect(
    host='10.16.48.219',
    user='root',
    password='111111',
    database='medical',
    cursorclass=pymysql.cursors.DictCursor
)
# 接受命令参数
args = sys.argv
# 去除第一个参数
params = args[1:-1]
# 查询数据库获取连接
query = "select * from Diabetes"
data = pd.read_sql(query, connection)

connection.close()
# 获取需要降维的特征
#features = data.drop(['Case_ID'],axis=1)
features = data[params]
# 数据预处理：处理缺失值
features = features.fillna(0)
# 特征标准化
scaler = StandardScaler()
scaled_features = scaler.fit_transform(features)
# 计算协方差矩阵
covariance_matrix = np.cov(scaled_features.T)
# 计算特征值和特征向量
eigenvalues, eigenvectors = np.linalg.eig(covariance_matrix)
# 选择主成分数量
# 这里假设选择保留前10个主成分
n_components = int(args[-1])
# 降维
pca = PCA(n_components=n_components)
reduced_features = pca.fit_transform(scaled_features)
result = pd.DataFrame(reduced_features)
#print(result)
print(result.to_dict())

# 计算每个主成分的贡献度
explained_variance_ratio = pca.explained_variance_ratio_
contribution_list = explained_variance_ratio.tolist()
for contribution in enumerate(contribution_list):
    print(contribution)

