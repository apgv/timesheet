package no.g_v.timesheet.domain

import javax.persistence.*

@Entity
@Table(name = 'projects')
class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'project_id')
    Long id

    @Column(name = 'name', nullable = false)
    String name

    @OneToOne
    @JoinColumn(name = 'person_id', nullable = false)
    Person owner

    protected Project() {
    }
}
