package com.gdu.ex.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionForward {
	private String path;  // Service 실행 후 이동할 경로를 저장합니다. forward는 jsp이름을 작성하고, redirect는 mapping을 작성합니다.
	private boolean isRedirect;  // 이동 방식을 저장합니다. forward는 false이고, redirect는 true입니다. boolean 타입의 필드는 디폴트 값으로 false 값을 가지므로 기본 이동 방식은 forward라는 의미가 코드에 나타나 있습니다.
}