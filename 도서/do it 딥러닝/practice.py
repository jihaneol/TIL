from collections import namedtuple
class Fruit:
    def __init__(self, name, price):
        self._name = name
        self._price = price
    
    def __str__(self):
        return 'Fruit Class Info : {}, {}'.format(self._name, self._price)
    
    def __add__(self, x):
        return self._price + x._price



s1 = Fruit('Orange', 7500)
s2 = Fruit('Banana', 3000)

print(s1 + s2)

class Vector:
    def __init__(self, *args):
        if len(args) ==0:
            self._x, self._y = 0, 0
        else:
            self._x, self._y = args
            
    def __repr__(self):
        return 'Vector(%r, %r)' % (self._x, self._y)

    def __add__(self, other):
        return Vector(self._x + other._x, self._y + other._y)
    
    
    def __mul__(self, y):
        return Vector(self._x* y, self._y*y)
    
    def __bool__(self):
        return bool(max(self._x, self._y))

   
Point = namedtuple('Point', 'x y') 

temp = [1.2, 14.2]
pt3 = Point(1.0, 5.0)
# _make() : 새로운 객체 생성
pt4 = Point._make(temp)
print(pt3)
print(pt4)

# _fields : 필드 네임 확인
print(pt4._fields)

# _asdict() : OrderedDict 반환
print(pt4._asdict())

# 실습

Classes = namedtuple('Classes', ['rank', 'number'])

numbers = [str(n) for n in range(1, 21)]
ranks = 'A B C D'.split()

print(numbers)
print(ranks)

# List Comprehension
students = [Classes(rank, number) for rank in ranks for number in numbers]

print(len(students))
print(students)

students2 = [Classes(rank, number) for rank in 'A B C D'.split() 
                                        for number in [str(n) for n in range(1,21)]]