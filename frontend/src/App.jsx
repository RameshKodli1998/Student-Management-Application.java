import { useState } from 'react'
import './App.css'

function App() {
  const [students, setStudents] = useState([
    { id: 101, name: 'Ramesh', course: 'Java' },
    { id: 102, name: 'Arun', course: 'React' }
  ])

  const [id, setId] = useState('')
  const [name, setName] = useState('')
  const [course, setCourse] = useState('')

  function deleteStudent(studentId) {

    function editStudent(studentId) {
      const student = students.find(s => s.id === studentId)

      const newName = prompt('Enter new student name:', student.name)
      if (newName === null || !newName.trim()) return

      const newCourse = prompt('Enter new course:', student.course)
      if (newCourse === null || !newCourse.trim()) return

      setStudents(students.map(s =>
          s.id === studentId
              ? { ...s, name: newName.trim(), course: newCourse.trim() }
              : s
      ))
    }

    setStudents(students.filter(student => student.id !== studentId))
  }
  function addStudent(event) {
    event.preventDefault()

    if (!id || !name.trim() || !course.trim()) {
      alert('Please fill all fields')
      return
    }

    if (students.some(student => student.id === Number(id))) {
      alert('Student ID already exists')
      return
    }

    const newStudent = {
      id: Number(id),
      name: name.trim(),
      course: course.trim()
    }

    setStudents([...students, newStudent])

    setId('')
    setName('')
    setCourse('')
  }

  return (
      <div className="app">
        <header className="header">
          <h1>Student Management System</h1>
          <p>Manage your students in one place</p>
        </header>

        <main>
          <section className="dashboard">
            <div className="card">
              <h2>{students.length}</h2>
              <p>Total Students</p>
            </div>

            <div className="card">
              <h2>{new Set(students.map(s => s.course)).size}</h2>
              <p>Total Courses</p>
            </div>

            <div className="card">
              <h2>{students.length}</h2>
              <p>Active Students</p>
            </div>
          </section>

          <section className="students">
            <h2>Add New Student</h2>

            <form onSubmit={addStudent}>
              <input
                  type="number"
                  placeholder="Student ID"
                  value={id}
                  onChange={e => setId(e.target.value)}
              />

              <input
                  type="text"
                  placeholder="Student Name"
                  value={name}
                  onChange={e => setName(e.target.value)}
              />

              <input
                  type="text"
                  placeholder="Course"
                  value={course}
                  onChange={e => setCourse(e.target.value)}
              />

              <button type="submit">Add Student</button>
            </form>

            <h2>Student Details</h2>

            <table>
              <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Course</th>
              </tr>
              </thead>

              <tbody>
              {students.map(student => (
                  <tr key={student.id}>
                    <td>{student.id}</td>
                    <td>{student.name}</td>
                    <td>{student.course}</td>
                    <td>
                      <button onClick={() => editStudent(student.id)}>
                        Edit
                      </button>

                      <button onClick={() => deleteStudent(student.id)}>
                        Delete
                      </button>
                    </td>
                  </tr>
              ))}
              </tbody>
            </table>
          </section>
        </main>
      </div>
  )
}

export default App