package parte2_semaforo.solucao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SolucaoSemaforo {
    private static int count = 0;
    private static final int THREADS = 8;
    private static final int INCREMENTOS_POR_THREAD = 200000;
    private static final int RESULTADO_ESPERADO = THREADS * INCREMENTOS_POR_THREAD;
    private static final Semaphore SEM = new Semaphore(1, true);

    public static void main(String[] args) throws InterruptedException {
        System.out.println(
                THREADS + " threads\n" + INCREMENTOS_POR_THREAD + " incrementos por thread\n" +
                        "Resultado esperado: " + THREADS * INCREMENTOS_POR_THREAD);
        long inicio = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < INCREMENTOS_POR_THREAD; j++) {

                    try {
                        SEM.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                    try{
                        count++;
                    }finally{
                        SEM.release();
                    }
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);

        long fim = System.currentTimeMillis();
        System.out.println("Valor final: " + count +
                "\nValor perdido por race conditions: " + (RESULTADO_ESPERADO - count) +
                "\nTempo total: " + (fim - inicio) + " ms");
    }
}
