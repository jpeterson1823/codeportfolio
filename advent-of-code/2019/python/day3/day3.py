a,b = open('day3.data').read().split('\n')
a,b = [x.split(',') for x in [a,b]]

dx = {'L': -1, 'R': 1, 'U': 0, 'D': 0}
dy = {'L': 0, 'R': 0, 'U': 1, 'D': -1}

def get_points(a):
    x = 0
    y = 0
    ans = set()
    for cmd in a:
        d = cmd[0]
        n = int(cmd[1:])
        assert d in ['L', 'R', 'U', 'D']
        for _ in range(n):
            x += dx[d]
            y += dy[d]
            ans.add((x,y))
    return ans

pa = get_points(a)
pb = get_points(b)
both = pa&pb
ans = min([abs(x)+abs(y) for (x,y) in both])
print(ans)