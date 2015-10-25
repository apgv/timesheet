package no.g_v.timesheet.domain

import javax.persistence.*
import java.time.YearMonth

@Entity
@Table(name = 'timesheets')
class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'timesheet_id')
    Long id

    @Column(name = 'period', nullable = false)
    YearMonth period

    @OneToOne
    @JoinColumn(name = 'person_id', nullable = false)
    Person owner

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timesheet")
    Set<TimesheetDay> timesheetDays

    protected Timesheet() {
    }
}
