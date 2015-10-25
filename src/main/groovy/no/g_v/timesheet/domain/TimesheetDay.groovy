package no.g_v.timesheet.domain

import javax.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = 'timesheet_days')
class TimesheetDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'timesheet_day_id')
    Long id

    @Column(name = 'date', nullable = false)
    LocalDate date

    @Column(name = 'check_in')
    LocalTime checkIn

    @Column(name = 'check_out')
    LocalTime checkOut

    @ManyToOne
    @JoinColumn(name = 'timesheet_id', nullable = false)
    Timesheet timesheet

    @OneToMany(cascade = CascadeType.ALL, mappedBy = 'timesheetDay')
    Set<TimesheetRecord> timesheetRecords

    protected TimesheetDay() {
    }
}
