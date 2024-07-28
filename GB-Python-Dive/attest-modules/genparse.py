import argparse
import logging

FORMAT = '{levelname:<8} -{asctime}. В модуле "{name}": \n\t{msg}'




parser = argparse.ArgumentParser(description="""Parser:
                                 calculates award for each name
                                 using arguments with corresponding indices """)

parser.add_argument('-n',action='store',nargs='*', help="Name: str")
parser.add_argument('-s',action = 'store',nargs='*',type=int,help='Salary value: int')
parser.add_argument('-b',action='store',nargs='*', help = 'Bonus value: int%%')
parser.add_argument('-fp',action='store',help = 'logfile',default=None, help= 'log file')
args = parser.parse_args()



names = args.n
salary = args.s
bonus = args.b

logging.basicConfig(format=FORMAT,filename=args.fp,encoding= 'utf-8',style="{",level=logging.NOTSET)
logger = logging.getLogger("genlog")

if not(len(names) == len(salary) == len(bonus)):
    e = ValueError("Arguments must be the same length")
    logger.error(e)
    raise e

def foo():
    result = {names[i]:salary[i]*int(bonus[i][:-1])*1e-2 for i in range(len(names))}
    logger.info(f"New data recieved: {result}")
    return result

if __name__ == '__main__':
    print(foo())





