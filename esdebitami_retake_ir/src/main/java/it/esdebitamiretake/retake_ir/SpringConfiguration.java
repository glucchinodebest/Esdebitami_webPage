package it.esdebitamiretake.retake_ir;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringConfiguration {
	@Bean
	public Docket api() {
		return

		new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.vvas.project.ir.controller")).paths(PathSelectors.any())
				.build().globalOperationParameters(commonParameters());
	}

	private List<Parameter> commonParameters() {

		List<Parameter> parameters = new ArrayList<Parameter>();

		/*parameters.add(new ParameterBuilder().name("accessToken").description("Token for authorization")
				.modelRef(new ModelRef("string")).parameterType("header").required(true).allowEmptyValue(false)
				.build());*/

		return parameters;
	}
}
