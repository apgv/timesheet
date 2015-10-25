package no.g_v.timesheet.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'persons')
class Person {

    @Id
    @Column(name = 'person_id')
    String id

    @Column(name = 'first_name', nullable = false)
    String firstName

    @Column(name = 'last_name', nullable = false)
    String lastName

    @Column(name = 'email', nullable = false)
    String email

    protected Person() {
    }

    Person(String id, String firstName, String lastName, String email) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }
}
