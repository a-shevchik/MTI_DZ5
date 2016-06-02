import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//Домашнее задание №5
public class ReflectionTest {

	public static void main(String[] args) {
		Object matrix1 = null, matrix2 = null, matrixOut = null, matrixIn = null;
		
		//1. Ссылка на ласс матрицы
		Class<Matrix> matrixClass = Matrix.class;
		
		//2. Создание экземпляров
		//Конструктор по умолчанию
		try {
			matrix1 = matrixClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//Перегруженный конструктор
		Constructor[] matrixConstructors = matrixClass.getConstructors();
		for (Constructor matrixCons : matrixConstructors){
			if (matrixCons.getParameterTypes().length == 2){
				try {
					matrix2 = matrixCons.newInstance(3, 3);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
		//3. Вывод на экран всех полей и методов
		System.out.println("Поля");
		for (Field matrixField : matrixClass.getDeclaredFields()){
			System.out.println("Поле: " + matrixField.getName());
		}
		System.out.println();
		
		System.out.println("Методы");
		for (Method matrixMethod : matrixClass.getDeclaredMethods()){
			System.out.println("Метод: " + matrixMethod.getName());
		}
		System.out.println();
		
		//4. Вызов методов сложения и вычитания
		//5. Выполнение приватного метода печати элементов матрицы
		double [][] arr_in = {{0, 1, 2, 3}, {8, 16, 10, 0}, {4, 34, 6, 2}, {1, 13, 25, 15}};
		matrixIn = arr_in;
		try {
			for (Method matrixMethod : matrixClass.getDeclaredMethods()){
				//Сложение:
				if ("MatrixSum".equalsIgnoreCase(matrixMethod.getName())){
					System.out.println("Сумма матриц");
					matrixOut = matrixMethod.invoke(matrix1, matrixIn);
					//Выводим в консоль полученную матрицу
					for (Method matrixPrintMethod : matrixClass.getDeclaredMethods()){
						if ("MatrixPrint".equalsIgnoreCase(matrixPrintMethod.getName())){
							matrixPrintMethod.setAccessible(true);//Обеспечение доступности приватного метода
							matrixPrintMethod.invoke(matrix1, matrixOut);//Вывод в консоль элементов матрицы
						}
					}
					System.out.println();
				}
				//вычитание:
				if ("MatrixDif".equalsIgnoreCase(matrixMethod.getName())){
					System.out.println("Разность матриц");
					matrixOut = matrixMethod.invoke(matrix1, matrixIn);
					//Выводим в консоль полученную матрицу
					for (Method matrixPrintMethod : matrixClass.getDeclaredMethods()){
						if ("MatrixPrint".equalsIgnoreCase(matrixPrintMethod.getName())){
							matrixPrintMethod.setAccessible(true);
							matrixPrintMethod.invoke(matrix1, matrixOut);
						}
					}
					System.out.println();
				}
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
	}

}
