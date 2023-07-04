package communication.chatgpt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "ChatCommand used by Unix OS",
                version = "0.0",
                description = "Unix CLI 환경에서 사용 가능한 chatGPT 명령어 만들기"
        )
)
@Configuration
public class SwaggerConfig {
}
