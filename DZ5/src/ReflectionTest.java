import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//�������� ������� �5
public class ReflectionTest {

	public static void main(String[] args) {
		Object matrix1 = null, matrix2 = null, matrixOut = null, matrixIn = null;
		
		//1. ������ �� ���� �������
		Class<Matrix> matrixClass = Matrix.class;
		
		//2. �������� �����������
		//����������� �� ���������
		try {
			matrix1 = matrixClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//������������� �����������
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
		
		//3. ����� �� ����� ���� ����� � �������
		System.out.println("����");
		for (Field matrixField : matrixClass.getDeclaredFields()){
			System.out.println("����: " + matrixField.getName());
		}
		System.out.println();
		
		System.out.println("������");
		for (Method matrixMethod : matrixClass.getDeclaredMethods()){
			System.out.println("�����: " + matrixMethod.getName());
		}
		System.out.println();
		
		//4. ����� ������� �������� � ���������
		//5. ���������� ���������� ������ ������ ��������� �������
		double [][] arr_in = {{0, 1, 2, 3}, {8, 16, 10, 0}, {4, 34, 6, 2}, {1, 13, 25, 15}};
		matrixIn = arr_in;
		try {
			for (Method matrixMethod : matrixClass.getDeclaredMethods()){
				//��������:
				if ("MatrixSum".equalsIgnoreCase(matrixMethod.getName())){
					System.out.println("����� ������");
					matrixOut = matrixMethod.invoke(matrix1, matrixIn);
					//������� � ������� ���������� �������
					for (Method matrixPrintMethod : matrixClass.getDeclaredMethods()){
						if ("MatrixPrint".equalsIgnoreCase(matrixPrintMethod.getName())){
							matrixPrintMethod.setAccessible(true);//����������� ����������� ���������� ������
							matrixPrintMethod.invoke(matrix1, matrixOut);//����� � ������� ��������� �������
						}
					}
					System.out.println();
				}
				//���������:
				if ("MatrixDif".equalsIgnoreCase(matrixMethod.getName())){
					System.out.println("�������� ������");
					matrixOut = matrixMethod.invoke(matrix1, matrixIn);
					//������� � ������� ���������� �������
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
