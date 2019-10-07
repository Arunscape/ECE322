import numpy as np
X = [i for i in range(0, 11)]
Y = [i for i in range(-5, 21)]
Z = [i for i in range(0, 8)]

possible_values_of_e = np.arange(-30, 101, 0.0001).tolist()
w = set([ (x, y, z) for x in X for y in Y for z in Z])


for e in possible_values_of_e:
    w1 = set([ i for i in w if max(abs(i[0]-1), abs(i[1]-1), abs(i[2]-1)) <= e])
    w2 = set([ i for i in w if max(abs(i[0]-5), abs(i[1]-10), abs(i[2]-4)) <= e])
    w3 = w - w1 - w2
    
    # w3 is automatically disjoint from w1 and w2 bevause w3 = w - w1 - w2
    if len( w1 & w2  ) == 0:
        print(e)


