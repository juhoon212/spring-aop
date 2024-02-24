package hello.advanced2.trace.threadlocal;

import hello.advanced2.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() throws Exception {
        // Given
         log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");
        Runnable userC = () -> fieldService.logic("userC");

        Thread threadC = new Thread(userC);

        threadC.start();

        Thread threadA = new Thread(userA);
        threadA.setName("thread A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread B");

        threadA.start();
        //sleep(2000); // 동시성 문제 발생 x
        sleep(100); // 동시성 문제 발생
        threadB.start();

        sleep(3000); // 메인쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}