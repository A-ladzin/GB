import argparse
import logging

FORMAT = '{levelname:<8} -{asctime}. В модуле "{name}": \n\t{msg}'


parser = argparse.ArgumentParser(description="""Parser""")

parser.add_argument(dest = 'edges',action='store',nargs=3, type = float, help="Edges: 3 float")
parser.add_argument('-fp',action='store',help = 'logfile',default=None)


args = parser.parse_args()

a,b,c = args.edges

logging.basicConfig(format=FORMAT,filename=args.fp,encoding= 'utf-8',style="{",level=logging.NOTSET)
logger = logging.getLogger("trianglelog")

def foo():
    if a + b > c and a + c > b and b + c > a:
        print("Треугольник существует")
        if a == b == c:
            logger.info(f"New equilateral triangle {a,b,c}")
            print("Треугольник равносторонний")
        elif a == b or b == c or a == c:
            logger.info(f"New isosceles triangle {a,b,c}")
            print("Треугольник равнобедренный")
        else:
            logger.info(f"New scalene triangle {a,b,c}")
            print("Треугольник разносторонний")
    else:
        logger.warning(f"No triangle we got{a,b,c}")
        print("Треугольник не существует")


if __name__ == '__main__':
    foo()