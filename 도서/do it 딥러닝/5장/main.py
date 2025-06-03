from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split
from SingleLayer import Single
import matplotlib.pyplot as plt
import numpy as np

cancer = load_breast_cancer()
x = cancer.data
y = cancer.target

x_train_all, x_test, y_train_all, y_test = train_test_split(x,y,stratify=y, test_size=0.2, random_state=42)

x_train, x_val, y_train, y_val = train_test_split(x_train_all, y_train_all, stratify=y_train_all, test_size=0.2, random_state=42)

print(cancer.feature_names[[2,3]])
plt.boxplot(x_train[:, 2:4])
plt.xlabel('feature')
plt.ylabel('value')
plt.show()

layer = Single()
layer.fit(x_train, y_train)
layer.score(x_val, y_val)