package du.kakaocrop.coupon.api.controller;


import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.domain.error.ApiException;
import du.kakaocrop.coupon.api.dto.BaseResDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/file/coupon")
public class CsvController {

    private JobLauncher jobLauncher;
    private final Job job;

    public CsvController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping("/csv")
        public BaseResDto csvFileReader(@RequestParam("fileName") String fileName){
        // date를 바꿔주면서 새로운 파라미터를 전달하는것으로 job 재실행
        // writer 부분에서는 현재 log로 출력.

        JobParameters params = new JobParametersBuilder()
                .addString("fileName", fileName)
                .addDate("date", new Date())
                .toJobParameters();
        try {
            jobLauncher.run(job, params);
        } catch (Exception e){
            e.printStackTrace();
            throw new ApiException(ErrorType.UNKNOWN_ERROR,"JOB 에러를 확인해주세요");
        }

        return new BaseResDto();
    }

}
