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

# 훈련 스케일 맞추기
train_mean = np.mean(x_train, axis=0)
train_std = np.std(x_train, axis=0)
x_train_scaled = (x_train - train_mean) / train_std

# 검증 스케일 맞추기
x_val_scaled = (x_val - train_mean) / train_std

layer = Single()
layer.fit(x_train_scaled, y_train ,x_val=x_val_scaled, y_val=y_val)
plt.ylim(0, 0.3)
plt.plot(layer.losses)
plt.plot(layer.val_losses)
plt.ylabel('loss')
plt.xlabel('epoch')
plt.legend(['train_loss', 'val_loss'])
plt.show()

print(layer.score(x_val_scaled, y_val))