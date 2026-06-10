package parte1_filosofos.deadlock;

public class Jantar {
    public static void main(String[] args) {
        final int n = 5;

        Garfo[] garfos = new Garfo[n];
        for (int i = 0; i < n; i++){
            garfos[i] = new Garfo(i);
        }

        for (int i = 0; i < n; i++){
            Garfo esquerdo = garfos[i];
            Garfo direito = garfos[(i + 1) % n];
            Filosofo filosofo = new Filosofo(i, esquerdo, direito);

            filosofo.start();
        }
    }
}