/**
 * Класс City представляет город с населением и площадью.
 * Предоставляет методы для вычисления плотности населения.
 */
class City {
    protected int population;
    protected double area;

    /**
     * Конструктор по умолчанию.
     * Устанавливает население и площадь в 0.
     */
    public City() {
        population = 0;
        area = 0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param pop количество населения
     * @param ar  площадь города
     */
    public City(int pop, double ar) {
        population = pop;
        area = ar;
    }

    /**
     * Вычисляет плотность населения.
     *
     * @return отношение населения к площади
     */
    public double populationDensity() {
        return population / area;
    }

    /**
     * Получить количество населения.
     *
     * @return население
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Получить площадь города.
     *
     * @return площадь
     */
    public double getArea() {
        return area;
    }
}

/**
 * Класс ResortTown расширяет City, добавляя дополнительное население.
 * Переопределяет метод вычисления плотности населения.
 */
class ResortTown extends City {
    private int additionalPopulation;

    /**
     * Конструктор по умолчанию.
     * Устанавливает все значения в 0.
     */
    public ResortTown() {
        population = 0;
        area = 0;
        additionalPopulation = 0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param population           основное население
     * @param area                 площадь
     * @param additionalPopulation дополнительное население
     */
    public ResortTown(int population, double area, int additionalPopulation) {
        super(population, area);
        this.additionalPopulation = additionalPopulation;
    }

    /**
     * Вычисляет плотность населения с учетом дополнительного населения.
     *
     * @return плотность населения
     */
    @Override
    public double populationDensity() {
        return (population + additionalPopulation) / area;
    }
}

/**
 * Абстрактный класс Country представляет страну с названием и
 * абстрактным методом расчета средней плотности населения.
 */
abstract class Country {
    protected String name;

    /**
     * Конструктор с параметром.
     *
     * @param n название страны
     */
    public Country(String n) {
        name = n;
    }

    /**
     * Абстрактный метод для расчета средней плотности населения.
     *
     * @return средняя плотность населения
     */
    public abstract double averagePopulationDensity();
}

/**
 * Класс FirstTown — страна с двумя обычными городами.
 */
class FirstTown extends Country {
    private City city1;
    private City city2;

    /**
     * Конструктор с параметрами.
     *
     * @param n     название страны
     * @param pop1  население первого города
     * @param area1 площадь первого города
     * @param pop2  население второго города
     * @param area2 площадь второго города
     */
    public FirstTown(String n, int pop1, double area1, int pop2, double area2) {
        super(n);
        city1 = new City(pop1, area1);
        city2 = new City(pop2, area2);
    }

    /**
     * Расчет средней плотности населения.
     *
     * @return сумма плотностей двух городов
     */
    @Override
    public double averagePopulationDensity() {
        return city1.populationDensity() + city2.populationDensity();
    }
}

/**
 * Класс SecondTown — страна с двумя курортными городами.
 */
class SecondTown extends Country {
    private ResortTown city1;
    private ResortTown city2;

    /**
     * Конструктор с параметрами.
     *
     * @param n              название страны
     * @param pop1           население первого города
     * @param area1          площадь первого города
     * @param pop2           население второго города
     * @param area2          площадь второго города
     * @param additionalPop1 дополнительное население первого города
     * @param additionalPop2 дополнительное население второго города
     */
    public SecondTown(String n, int pop1, double area1, int pop2, double area2, int additionalPop1, int additionalPop2) {
        super(n);
        city1 = new ResortTown(pop1, area1, additionalPop1);
        city2 = new ResortTown(pop2, area2, additionalPop2);
    }

    /**
     * Расчет средней плотности населения.
     *
     * @return сумма плотностей двух курортных городов
     */
    @Override
    public double averagePopulationDensity() {
        return city1.populationDensity() + city2.populationDensity();
    }
}

/**
 * Класс Combine — страна с обычным и курортным городом.
 */
class Combine extends Country {
    private City city1;
    private ResortTown city2;

    /**
     * Конструктор с параметрами.
     *
     * @param n              название страны
     * @param pop1           население обычного города
     * @param area1          площадь обычного города
     * @param pop2           население курортного города
     * @param area2          площадь курортного города
     * @param additionalPop2 дополнительное население курортного города
     */
    public Combine(String n, int pop1, double area1, int pop2, double area2, int additionalPop2) {
        super(n);
        city1 = new City(pop1, area1);
        city2 = new ResortTown(pop2, area2, additionalPop2);
    }

    /**
     * Расчет средней плотности населения.
     *
     * @return сумма плотностей обычного и курортного города
     */
    @Override
    public double averagePopulationDensity() {
        return city1.populationDensity() + city2.populationDensity();
    }
}

/**
 * Главный класс приложения для расчета плотности населения.
 * <p>Формула расчета плотности населения:</p>
 * <pre>{@code
 * density = population / area
 * }</pre>
 *
 * <h2>Таблица плотности населения для городов:</h2>
 * <table border="1">
 *     <tr>
 *         <th>Город</th>
 *         <th>Население</th>
 *         <th>Площадь</th>
 *         <th>Плотность населения</th>
 *     </tr>
 *     <tr>
 *         <td>Москва</td>
 *         <td>200000</td>
 *         <td>80.0</td>
 *         <td>2500</td>
 *     </tr>
 *     <tr>
 *         <td>Санкт-Петербург</td>
 *         <td>150000</td>
 *         <td>70.2</td>
 *         <td>2135.9</td>
 *     </tr>
 *     <tr>
 *         <td>Комбинированный город</td>
 *         <td>350000</td>
 *         <td>80.0</td>
 *         <td>4375</td>
 *     </tr>
 * </table>
 

 * <h2>Граф:</h2>
 * <p><img src="C:\Jvsc\graph.png" width="600" alt="Graph of Class Dependencies"/></p>
 */
public class Main {
    public static void main(String[] args) {
        double k, k1, k2;

        // Первый город
        FirstTown firstTown = new FirstTown("Москва", 200000, 80.0, 10000, 25.5);
        k = firstTown.averagePopulationDensity();
        System.out.println(k);

        // Второй город
        SecondTown secondTown = new SecondTown("Санкт-Петербург", 150000, 70.2, 10000, 25.5, 5000, 2500);
        k1 = secondTown.averagePopulationDensity();
        System.out.println(k1);

        // Комбинированный город
        Combine combine = new Combine("Комбинированный", 350000, 80.0, 10000, 25.5, 2500);
        k2 = combine.averagePopulationDensity();
        System.out.println(k2);
    }
}
