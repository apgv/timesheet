package no.g_v.timesheet.domain

import javax.persistence.*
import java.time.LocalDateTime
import java.time.YearMonth

@Entity
@Table(name = 'timesheets')
class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'timesheet_id')
    Long id

    @Column(name = 'period')
    YearMonth period

    @Column(name = 'last_updated')
    LocalDateTime lastUpdated

    @Override
    String toString() {
        "Timesheet{" +
                "id=" + id +
                ", period=" + period +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
