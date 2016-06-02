
public class Matrix {
	private int m, n;
	double arr[][];
	
	static double[][] sing_m = new double[3][3];
	
	//Умолчательный конструктор класса
	public Matrix () {
		m=3;
		n=3;
		arr=new double [m][n];
		
		System.out.println("Текущая матрица:");
		//Заполнение матрицы произвольными числами
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				
				arr[i][j]= (int) Math.round(Math.random()*100);
				System.out.print(arr[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	//Вспомогательный конструктор класса
	public Matrix (int m1, int n1) {
		m=m1;
		n=n1;
		
		arr=new double [m][];
		
		for (int i=0; i<m; i++) {
			arr[i]=new double [n];
		}
		
		System.out.println("Текущая матрица:");
		//Заполнение матрицы произвольными числами
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				
				arr[i][j]= (int) Math.round(Math.random()*100);
				System.out.print(arr[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	//1. Сумма матриц
	public double[][] MatrixSum (double arr2[][]) {
		//Размерность текущей матрицы: m x n
		double [][] arr3 = new double [m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				arr3[i][j]=arr[i][j] + arr2[i][j];
			}
		}
		
		return arr3;
	}
	
	//2. Разность матриц
	public double[][] MatrixDif (double arr2[][]) {
		//Размерность текущей матрицы: m x n
		double [][] arr3 = new double [m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				arr3[i][j]=arr[i][j] - arr2[i][j];
			}
		}
		
		return arr3;
	}
	
	//3. Печать элементов матрицы в консоль
	private void MatrixPrint (double arr_prn[][]) {
		for (int i=0; i<arr_prn.length; i++){
			for (int j=0; j<arr_prn[i].length; j++){
				System.out.print(arr_prn[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//4. Умножение текущей матрицы на число
	public double [][] MatrixNumMult (int mult){
		double [][] arr_out = new double [m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				arr_out[i][j]=arr[i][j] * mult;
			}
		}
		return arr_out;
	}
	
	//5. Умножение матриц
	public double[][] MatrixMult (double[][] arr_in) {
		double [][] arr_out = new double [arr_in.length][arr[0].length];
		
		//Проверка на согласованность
		//Количество строк матрицы arr_in должно равняться количеству столбцов матрицы arr
		
		if (arr_in[0].length != arr.length) {
			System.out.println("Матрицы не согласованы");
			return null;
		}
		else
		{
			for (int i=0; i<m; i++) {
				for (int j=0; j<n; j++) {
					for (int k=0; k<m; k++){
						arr_out[i][j]+=arr_in[i][k] * arr[k][j];
					}
				}
			}
			return arr_out;
		}
	}
	
	//6. Вычисление определителя
    //рекурсивная функция - вычисляет значение определителя. Если на входе определитель 2х2 - просто вычисляем (крест-на-крест), иначе раскладываем на миноры. Для каждого минора вычисляем ЕГО определитель, рекурсивно вызывая ту же функцию..
    public double DetCalc(double[][] arr_in){
    	double calcResult=0;
        if (arr_in.length==2){
            calcResult=arr_in[0][0]*arr_in[1][1]-arr_in[1][0]*arr_in[0][1];
        }
        else{
            int koeff=1;
            for(int i=0; i<arr_in.length; i++){
                if(i%2==1){  //Т.к. разложение идет всегда по первой (читай - "нулевой") строке, то фактически проверяется на четность значение i+0.
                    koeff=-1;
                }
                else{
                    koeff=1;
                };
      //собственно разложение:                
                calcResult += koeff*arr_in[0][i]*this.DetCalc(this.GetMinor(arr_in,0,i)); 
            }
        }
    //возвращаем ответ
        return calcResult;
    }
 
 
    //функция, к-я возвращает нужный нам минор. На входе - определитель, из к-го надо достать минор и номера строк-столбцов, к-е надо вычеркнуть.
    private double[][] GetMinor(double[][] arr_in, int row, int column){
        int minorLength = arr_in.length-1;
        double[][] minor = new double[minorLength][minorLength];
        int dI=0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        int dJ=0;
        for(int i=0; i<=minorLength; i++){
            dJ=0;
            for(int j=0; j<=minorLength; j++){
                if(i==row){
                    dI=1;
                }
                else{
                    if(j==column){
                        dJ=1;
                    }
                    else{
                        minor[i-dI][j-dJ] = arr_in[i][j];
                    }
                }
            }
        }
         
        return minor;
    }
     
    //7. Вычисление обратной матрицы
    public double[][] InverseMatrix(double[][] arr_in){
    	double[][] A = new double[arr_in.length][arr_in[0].length];
        int n = arr_in.length;
        int row[] = new int[n];
        int col[] = new int[n];
        double temp[] = new double[n];
        int hold, I_pivot, J_pivot;
        double pivot, abs_pivot;

        if (A[0].length != n) {
            System.out.println("Матрица должна быть квадратной");
            return null;
        }

        //Копирование входной матрицы
        for (int i=0; i<arr_in.length; i++){
        	for (int j=0; j<arr_in[0].length; j++){
        		A[i][j] = (double)arr_in[i][j];
        	}
        }
        
        // установиим row и column как вектор изменений.
        for (int k = 0; k < n; k++) {
			row[k] = k;
			col[k] = k;
        }
        // начало главного цикла
        for (int k = 0; k < n; k++) {
            // найдем наибольший элемент для основы
			pivot = A[row[k]][col[k]];
			I_pivot = k;
			J_pivot = k;
            
			for (int i = k; i < n; i++) {
                for (int j = k; j < n; j++) {
                	abs_pivot = Math.abs(pivot);
                    if (Math.abs(A[row[i]][col[j]]) > abs_pivot) {
					I_pivot = i;
					J_pivot = j;
					pivot = A[row[i]][col[j]];
                    }
                }
            }
            if (Math.abs(pivot) < 1.0E-10) {
                System.out.println("Матрица является вырожденной!");
                return null;
            }
            //перестановка к-ой строки и к-ого столбца со столбцом и строкой, содержащий основной элемент(pivot основу)
			hold = row[k];
			row[k] = row[I_pivot];
			row[I_pivot] = hold;
			hold = col[k];
			col[k] = col[J_pivot];
			col[J_pivot] = hold;
            
			// k-ую строку с учетом перестановок делим на основной элемент
			A[row[k]][col[k]] = 1.0 / pivot;
            for (int j = 0; j < n; j++) {
                if (j != k) {
                	A[row[k]][col[j]] = A[row[k]][col[j]] * A[row[k]][col[k]];
                }
            }
            // внутренний цикл
            for (int i = 0; i < n; i++) {
                if (k != i) {
                    for (int j = 0; j < n; j++) {
                        if (k != j) {
                        	A[row[i]][col[j]] = A[row[i]][col[j]] - A[row[i]][col[k]] * A[row[k]][col[j]];
                        }
                    }
                    A[row[i]][col[k]] = -A[row[i]][col[k]] * A[row[k]][col[k]];
                }
            }
        }
        // конец главного цикла

        // переставляем назад rows
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
            	temp[col[i]] = A[row[i]][j];
            }
            for (int i = 0; i < n; i++) {
            	A[i][j] = temp[i];
            }
        }
        // переставляем назад columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	temp[row[j]] = A[i][col[j]];
            }
            for (int j = 0; j < n; j++) {
            	A[i][j] = temp[j];
            }
        }
        return A;
    }
	
	//8. Возврат единичной матрицы
    static {
    	for (int i=0; i<3; i++){
    		for (int j=0; j<3; j++){
    			if (i == j) sing_m[i][j] = 1;
    			else sing_m[i][j] = 0;
    		}
    	}
    }
	
	//9. Возврат размерностей матрицы
    public int get_dim(double m_in[][], char d_vid){
    	//d_vid = 'r' - возвращает количество строк
    	//d_vid = 'c' - возвращает количество колонок
    	
    	if (d_vid == 'r') return m_in[0].length;
    	else if (d_vid == 'c') return m_in.length;
    	else return 0;
    	
    }
	
	
	
	
	
	
	public static void main(String[] args) {
		//Входящая матрица 4 х 4:
		double [][] arr_in = {{0, 1, 2, 3}, {8, 16, 10, 0}, {4, 34, 6, 2}, {1, 13, 25, 15}};
		double [][] arr_out = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

		//Инициализация класса
		Matrix M = new Matrix(4, 4);
		
		System.out.println("Входящая матрица:");
		M.MatrixPrint(arr_in);

		//Сумма матриц
		arr_out = M.MatrixSum(arr_in);
		System.out.println("Результирующая матрица (сумма):");
		M.MatrixPrint(arr_out);
		
		//Разность матриц
		arr_out = M.MatrixDif(arr_in);
		System.out.println("Результирующая матрица (разность):");
		M.MatrixPrint(arr_out);
		
		//Умножение матрицы на число
		arr_out = M.MatrixNumMult(14);
		System.out.println("Результирующая матрица (умножение на число 14):");
		M.MatrixPrint(arr_out);
		
		//Произведение матриц
		arr_out = M.MatrixMult(arr_in);
		System.out.println("Результирующая матрица (произведение):");
		M.MatrixPrint(arr_out);
		
		//Вычисление определителя
		double Result = M.DetCalc(arr_in);
		System.out.println("Определитель для входящей матрицы: "+Result);
		System.out.println();
		
		//Вычисление обратной матрицы
		arr_out = M.InverseMatrix(arr_in);
		System.out.println("Результирующая матрица (обратная):");
		M.MatrixPrint(arr_out);
		
		//Единичная матрица 3 х 3
		System.out.println("Единичная матрица:");
		M.MatrixPrint(sing_m);
		
		//Возврат размерностей
		System.out.println("Количество строк входящей матрицы: "+M.get_dim(arr_in, 'r'));
		System.out.println("Количество столбцов входящей матрицы: "+M.get_dim(arr_in, 'c'));
		System.out.println();
		
	}
	
}
