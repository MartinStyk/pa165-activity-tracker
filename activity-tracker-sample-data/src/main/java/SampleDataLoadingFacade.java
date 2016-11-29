import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Fill database with sample data
 *
 * @author Martin Styk
 * @version 29.11.2016
 */
@Component
@Transactional
public interface SampleDataLoadingFacade {

    void loadData();

}
