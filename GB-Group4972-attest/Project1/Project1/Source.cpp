
#include <iostream>
#include <fstream>
using namespace std;



int main()
{


				// Uncomment to use Manual Input
	//ofstream out;
	//cout << "Enter an array of comma-separated elements" << endl;
	//out.open("data.txt");
	//char data[1000];
	//cin.getline(data, 1000);
	//out << data;
	//out.close();

	


	ifstream in;
	in.open("data.txt");


	int size = 0;
	char** s = new char* [size];
	int* w_sizes = new int[size];

	while (!in.eof()) {
		char* w = new char[0];
		int n = 0;
		char* temp;
		char ch = in.get();
		bool start_flag = true;
		while (ch != ',' && !in.eof())
		{
			while (ch == ' ' && !in.eof() && start_flag) ch = in.get();
			start_flag = false;
			temp = w;
			n++;
			w = new char[n];
			for (int i = 0; i < n - 1; i++)
			{
				*(w + i) = *(temp + i);
			}
			*(w + n - 1) = ch;
			ch = in.get();
			delete[] temp;
		}
		if (n > 0)
		{
			size++;

			int* temp_w_sizes = w_sizes;
			

			w_sizes = new int[size];

			for (int i = 0; i < size - 1; i++)
			{
				w_sizes[i] = temp_w_sizes[i];
			}
			delete[] temp_w_sizes;
			w_sizes[size - 1] = n;


			char** temp_s = s;
			



			s = new char* [size];

			for (int i = 0; i < size - 1; i++)
			{
				s[i] = new char[w_sizes[i]];
				for (int j = 0; j < w_sizes[i]; j++)
				{
					s[i][j] = temp_s[i][j];
				}
			}

			for (int i = 0; i < size - 1; i++)
			{
				delete[]temp_s[i];
			}
			delete[] temp_s;


			s[size - 1] = new char[n];
			for (int j = 0; j < n; j++)
			{
				s[size - 1][j] = w[j];
			}
			
		}

	}
	in.close();


	int result_size = 0;
	char** result = new char*[result_size];
	int* result_w_sizes = new int[result_size];

	cout << "Data:\n";
	for (int i = 0; i < size; ++i)
	{
		cout << '"';
		for (int j = 0; j < w_sizes[i]; j++) {
			cout << (char)s[i][j];
		}
		cout << '"';
		cout << " ";
		if (w_sizes[i] < 4) {

			result_size++;

			int* temp_result_w_sizes = result_w_sizes;

			result_w_sizes = new int[result_size];

			for (int k = 0; k < result_size - 1; k++)
			{
				result_w_sizes[k] = temp_result_w_sizes[k];
			}
			result_w_sizes[result_size - 1] = w_sizes[i];

			delete[] temp_result_w_sizes;




			char** temp_result = result;
			result = new char* [result_size];
			for (int k = 0; k < result_size-1; k++)
			{
				result[k] = temp_result[k];
			}
			result[result_size - 1] = s[i];
			
			delete[] temp_result;
		}

		
	}

	cout << "\nResult:\n";

	for (int i = 0; i < result_size; ++i)
	{
		cout << '"';
		for (int j = 0; j < result_w_sizes[i]; j++) {
			
			cout << (char)result[i][j];
			
		}
		cout << '"';
		cout << " ";

	}
	


}