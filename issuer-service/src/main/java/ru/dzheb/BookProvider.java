package ru.dzheb;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.dzheb.api.Bookie;

@Service
public class BookProvider {

    private final WebClient webClient;

    //    private final EurekaClient eurekaClient;
//    public BookProvider(EurekaClient eurekaClient){
//        webClient = WebClient.builder().build();
//        this.eurekaClient = eurekaClient;
//    }
    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
//    this.eurekaClient = eurekaClient;
    }

    //    public UUID getRandomBookId(){
//        UUID randomBook = webClient.get()
    public Bookie getRandomBook() {
        Bookie randomBook = webClient.get()
                .uri("http://book-service/api/book/random")
//      .uri(getBookServiceIp() + "/api/book/random")
                .retrieve()
                .bodyToMono(Bookie.class)
                .block();
        return randomBook;
//return randomBook.getUuid();
    }
//    private String getBookServiceIp() {
//        Application application = eurekaClient.getApplication("BOOK-SERVICE");
//        List<InstanceInfo> instances = application.getInstances();
//        int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
//    InstanceInfo randomInstance = instances.get(randomIndex);
//      return "http://" +   randomInstance.getIPAddr() + ":" +
//                randomInstance.getPort();
//    }
//    @Data
//    private static class UUIDBookResponse{
//        private UUID uuid;

//}

}
