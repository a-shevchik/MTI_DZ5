import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)//Аннотация учитывается на этапе компиляции
@Target(ElementType.CONSTRUCTOR)//Применяется к конструктору
public @interface Quadratic {
}
