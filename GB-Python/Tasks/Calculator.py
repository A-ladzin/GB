
string = input("Enter an Expression: ")
expressions = []
numbers = []

def error():
    print("Something Went Wrong")
    return None

def parser(string):
    num = False
    sign = False
    point = False
    point_position = 1
    temp = 0
    priority = 0
    for ch in string:
        if ch > chr(47) and ch < chr(58):
            if num:
                if point:
                    point_position *= 10
                else:
                    temp *=10
            else:
                num = not num
            temp += float(ch)/point_position
        else:
            if num:
                num = not num
                if sign:
                    temp *=-1
                    sign = False
                if ch == '.':
                    point = True
                    num = not num
                    continue
                numbers.append(temp)
                
            if ch == '*':
                    expressions.append(((lambda x,y:x*y), 1+priority*3))
            elif ch == '+':
                    expressions.append(((lambda x,y:x+y), 0+priority*3))
            elif ch == '-':
                    expressions.append(((lambda x,y:x-y), 0+priority*3))
            elif ch == '/':
                    expressions.append(((lambda x,y:x/y), 1+priority*3))
            elif ch == '^':
                    expressions.append(((lambda x,y:x**y), 2+priority*3))
            elif ch == ' ':
                continue
            elif ch == '-':
                sign = True
            elif ch == '(':
                    priority+=1
            elif ch == ')':
                    priority-=1
            if priority < 0:
                return error()
            point = False
            point_position = 1

            temp = 0
    if (num):
        if(sign):
            temp *=-1
        numbers.append(temp)
    if priority != 0:
        return error()
    

def calculate(priority = -1):
    second_number = numbers.pop()
    if not expressions:
        return second_number
    if priority < expressions[-1][1]:
        while expressions:
            expr = expressions.pop()
            second_number = expr[0](calculate(expr[1]), second_number)
    return second_number

        
        
    
        
parser(string)

print(calculate()) 
    
    
                