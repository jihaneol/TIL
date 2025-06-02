import numpy as np
class Single:
    def __init__(self):
        self.w = None
        self.b = None
        self.losses = []
        
    def forpass(self, x):
        z = np.sum(x * self.w) + self.b
        return z

    def backprop(self, x, err):
        w_grad = x*err
        b_grad = 1*err
        return w_grad, b_grad
    
    def fit(self, x,y, epochs=100):
        self.w = np.ones(x.shape[1])
        self.b = 0
        for i in range(epochs):
            loss = 0
            indexes = np.random.permutation(np.arange(len(x))) #인덱스 무작위 섞이
            for i in indexes:
                x_i = x[i]
                y_i = y[i]
                z = self.forpass(x_i)
                a = self.activation(z)
                err = -(y_i - a)
                w_grad, b_grad = self.backprop(x_i, err)
                self.w -= w_grad
                self.b -= b_grad
                
                a = np.clip(a, 1e-10, 1-1e-10)
                
                loss += -(y[i]*np.log(a)+(1-y[i])*np.log(1-a))
            
            self.losses.append(loss/len(y))
    
    def activation(self, z):
        z = np.clip(z, -100, None) #안전한 np.exp를 위해
        a = 1 / (1+np.exp(-z))
        return a
    
    
    def predict(self, x):
        z = [self.forpass(x_i) for x_i in x]
        return np.array(z) > 0 
                
    
    def score(self, x, y):
        return np.mean(self.predict(x)==y)