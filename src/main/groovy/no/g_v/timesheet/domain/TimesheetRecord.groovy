package no.g_v.timesheet.domain

import javax.persistence.*

@Entity
@Table(name = 'timesheet_records')
class TimesheetRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'timesheet_record_id')
    Long id

    @ManyToOne
    @JoinColumn(name = 'timesheet_day_id', nullable = false)
    TimesheetDay timesheetDay

    @ManyToOne
    @JoinColumn(name = 'project_id', nullable = false)
    Project project

    @Column(name = 'hours')
    Double hours

    @Column(name = 'comment')
    String comment

    protected TimesheetRecord() {
    }
}
