package du.kakaocrop.coupon.bootstrap.configure;

import du.kakaocrop.coupon.api.dao.CouponDao;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.job.CsvJobListener;
import du.kakaocrop.coupon.api.job.processor.CouponProcessor;
import du.kakaocrop.coupon.api.job.writer.CouponWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    public final CouponDao couponDao;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, CouponDao couponDao) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.couponDao = couponDao;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<CouponCode> csvAnimeReader(@Value("#{jobParameters[fileName]}") String fileName) {
        FlatFileItemReader<CouponCode> reader = new FlatFileItemReader<CouponCode>();
        reader.setResource(new ClassPathResource(fileName));
        reader.setLineMapper(new DefaultLineMapper<CouponCode>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"seq", "couponSeq", "couponCode","targetUserId"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CouponCode>() {{
                setTargetType(CouponCode.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<CouponCode, CouponCode> csvProcessor() {
        return new CouponProcessor();
    }

    @Bean
    public CouponWriter<CouponCode> writer()
    {
        return new CouponWriter<CouponCode>(couponDao);
    }

    @Bean
    public Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<CouponCode, CouponCode>chunk(500)
                .reader(csvAnimeReader(null))
                .processor(csvProcessor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job csvFileReadingJob(CsvJobListener csvJobListener){
        return jobBuilderFactory.get("csvFileReadingJob")
                .incrementer(new RunIdIncrementer())
                .listener(csvJobListener)
                .flow(csvFileToDatabaseStep())
                .end()
                .build();
    }

}
