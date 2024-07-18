        
string[,] table = new string[2,3];
int[,] matrix = new int[86,27];

table[1,2] = "Hello";

int k = 0;
for (int i = 0; i < matrix.GetLength(0); i++){
    for (int j = 0; j < matrix.GetLength(1); j++){
        matrix[i,j] = k;
        k++;
    }
}
int temp = matrix.Length;
int count = 0;
while(temp!=0){
    count++;
    temp/=10;
}

for (int i = 0; i < matrix.GetLength(0); i++){
    for (int j = 0; j < matrix.GetLength(1); j++){
        temp = matrix[i,j];
        int curr_count=0;
        while(temp!=0){
            curr_count++;
            temp/=10;
        }
        if(matrix[i,j]==0) curr_count++;
        Console.Write($"{matrix[i,j]} ");
        for(int n = 0; n < count-curr_count; n++){
            Console.Write(' ');
        }
    }
    Console.WriteLine();
}