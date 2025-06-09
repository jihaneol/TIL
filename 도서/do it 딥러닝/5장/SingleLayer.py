import numpy as np
class Single:
    def __init__(self, leaning_rate=0.1):
        self.w = None
        self.b = None
        self.losses = []
        self.val_losses = [] # 검증 손실을 기록
        self.w_history = []
        self.lr = leaning_rate
        
    def forpass(self, x):
        z = np.sum(x * self.w) + self.b
        return z

    def backprop(self, x, err):
        w_grad = x*err
        b_grad = 1*err
        return w_grad, b_grad
    
    def fit(self, x,y, epochs=100, x_val=None, y_val=None):
        self.w = np.ones(x.shape[1])
        self.b = 0
        self.w_history.append(self.w.copy()) # 가중치 기록
        np.random.seed(42)
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
                self.w -= self.lr*w_grad # 가중치 업데이트(학습률 적용)
                self.b -= b_grad # 절편 업데이트
                
                self.w_history.append(self.w.copy()) # 가중치 기록

                a = np.clip(a, 1e-10, 1-1e-10)
                
                loss += -(y[i]*np.log(a)+(1-y[i])*np.log(1-a))
            
            # 훈련 세트의 평균 손실을 저장합니다.
            self.losses.append(loss/len(y))
            # 검증 세트에 대한 손실을 계산합니다.
            self.update_val_loss(x_val, y_val)
    
    def activation(self, z):
        z = np.clip(z, -100, None) #안전한 np.exp를 위해
        a = 1 / (1+np.exp(-z))
        return a
    
    def update_val_loss(self, x_val, y_val):
        if x_val is None:
            return
        val_loss = 0
        for i in range(len(x_val)):
            z = self.forpass(x_val[i])
            a = self.activation(z)
            a = np.clip(a, 1e-10, 1-1e-10)
            val_loss += -(y_val[i]*np.log(a)+(1-y_val[i])*np.log(1-a))
        self.val_losses.append(val_loss/len(y_val))
    
    def predict(self, x):
        z = [self.forpass(x_i) for x_i in x]
        return np.array(z) > 0 
                
    
    def score(self, x, y):
        return np.mean(self.predict(x)==y)