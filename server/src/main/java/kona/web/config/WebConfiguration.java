package kona.web.config;

/**
 *
 * @EnableWebMvc annotation does a number of useful things – specifically, in
 *               the case of REST, it detects the existence of Jackson and JAXB
 *               2 on the classpath and automatically creates and registers
 *               default JSON and XML converters.
 *
 * @author jnaalisv
 *
 */
//@Configuration
//@EnableWebMvc
//@Import(SerializationConfig.class)
//@ComponentScan({"kona.web.interfaces"})
public class WebConfiguration// extends WebMvcConfigurerAdapter
{

//    private final ObjectMapper objectMapper;
//
//    public WebConfiguration(final ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
//        converters.add(new StringHttpMessageConverter());
//    }

}

