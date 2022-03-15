package main.java.com.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author louwei
 * @ClassName: CAS
 * @Description:
 */
@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}

/**
 * 示例: "原子引用"
 *
 * 注意 --> 会出现 'ABA'问题
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User user1 = new User("lou", 12);
        User user2 = new User("wei", 13);

        //封装为原子引用
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);

        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t" + atomicReference.get().toString());
    }
}
