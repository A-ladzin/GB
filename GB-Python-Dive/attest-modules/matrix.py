import argparse
import logging
import numpy as np


FORMAT = '{levelname:<8} -{asctime}. В модуле "{name}": \n\t{msg}'


parser = argparse.ArgumentParser(description="""Parser""")

parser.add_argument(dest = 'elements',action='store',nargs='*', type = float, help="elements: float")
parser.add_argument('-r',action = 'store',type = int, default = None, help="number of rows: int , default = square root of number of elements")
parser.add_argument('-fp',action='store',help = 'logfile',default=None)


args = parser.parse_args()

if args.r is None:
    n_rows = int(np.sqrt(len(args.elements)))
else:
    n_rows = args.r

elements = None
while elements is None:
    try:
        elements = np.array(args.elements).reshape(-1,n_rows)
    except ValueError:
        if n_rows < 2:
            elements = np.array(args.elements).reshape(1, -1)
        n_rows-=1


logging.basicConfig(format=FORMAT,filename=args.fp,encoding= 'utf-8',style="{",level=logging.NOTSET)
logger = logging.getLogger("matrixlog")

def transpose(matrix):
    result = [list(r) for r in np.array(matrix).T]
    s = '\n'.join([str(i) for i in result])
    logger.info(
f"""new matrix:
{s}
""")
    return result


if __name__ == '__main__':
    print(transpose(elements))
