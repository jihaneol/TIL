from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split
from LogisicNeuron import LogistiNeuron
from SingleLayer import Single
import matplotlib.pyplot as plt
import numpy as np

cancer = load_breast_cancer()

x = cancer.data
y = cancer.target

x_train, x_test, y_train, y_test = train_test_split(x,y,stratify=y, test_size=0.2, random_state=42)

neuron = LogistiNeuron()
neuron.fit(x_train, y_train)

print(np.mean(neuron.predict(x_test) == y_test))

layer = Single()
layer.fit(x_train, y_train)
print(layer.score(x_test, y_test))


plt.plot(layer.losses)
plt.xlabel('epoch')
plt.ylabel('loss')
plt.show()
