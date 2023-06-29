package honeybee.springboot.springbootweb.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@RequiredArgsConstructor: 주요 필드만을 인자로 받는 생성자를 생성합니다. final이나 @NonNull으로 선언된 필드들을 대상으로 합니다.
//                          즉, 필수적으로 초기화해야 하는 필드들을 매개변수로 받는 생성자를 생성합니다.
//@NoArgsConstructor: 매개변수 없는 기본 생성자를 생성합니다. 모든 필드에 대해 기본 값을 할당하지 않은 경우 사용될 수 있습니다.
//@AllArgsConstructor: 모든 필드를 인자로 받는 생성자를 생성합니다. 클래스의 모든 필드에 대한 매개변수를 포함하는 생성자를 자동으로 생성합니다.

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;


}
