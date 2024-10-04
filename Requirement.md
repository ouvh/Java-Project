
# Requirement Analysis Document

## Project Name: Collaborative Project Management Platform

### 1. Project Overview
The platform facilitates collaborative project work, allowing admins to invite members, manage tasks, and share files both publicly and privately. Members will have access to a personalized to-do list that tracks task statuses and deadlines. The platform is built using **Java** with **Hibernate** and **Spring Boot** for the back-end, while **Firebase** may be used for real-time data management and either **React** or **Vue** for the front-end.

### 2. Stakeholders
- **Admin**: Responsible for creating projects, inviting members, managing file sharing, and overseeing the overall progress.
- **Members**: Users working on assigned tasks, managing their to-do lists, and tracking deadlines.
- **Public Users**: Individuals who can view publicly shared files without requiring platform registration.

### 3. Functional Requirements

#### 3.1 Project Management
- **Project Details**: Each project will have attributes such as:
  - **Type**: String (Project category)
  - **Project Name**: String (Name of the project)

#### 3.2 User Management
- **Admin Role**:  
  - Admins can create projects, invite members, and manage file permissions.
  - Admins can create and assign tasks to members and control access to files and folders.
  
- **Member Role**:  
  - Users can register, log in, and join projects upon invitation.
  - Each member can view assigned tasks, track deadlines, and update task statuses.
  - Members can upload files to shared folders and manage project-specific files.

#### 3.3 Communication
- **Chat Group**: Each project will include a chat group where members can communicate.
  - **Message Sender**: String (The name of the user sending the message)
  - **Message Content**: String (The text content of the message)

#### 3.4 File Sharing and Document Management
- **Document Space**: Admins and members can upload files to project folders.
  - **Folder Name**: String (Name of the folder)
  - **Document Storage Link**: Links to stored files for retrieval.
  
  **Firebase** may be used to store and manage documents in real-time.

#### 3.5 Task Management
- **Task List**: Projects will include task lists with individual tasks.
  - **Task Deadline**: Date (Due date of the task)
  - **Students Concerned**: Array (List of members responsible for the task)
  - **Task Description**: Text (Details about the task)
  - **Task Status**: Text (Indicates the current state: To Do, In Progress, Completed)

#### 3.6 Invitations and Requests
- **Project Invitations**: Admins can invite users to join a project.
  - **From**: Admin or user sending the invitation
  - **To**: User receiving the invitation
  
- **Project Join Requests**: Users can request to join a project.
  - **From**: Requesting user
  - **To**: Admin managing the request
  - **Message**: Optional text explaining the request

#### 3.7 User Profile
- **User Profile Details**:  
  - **Email**: User's email address
  - **Username**: Displayed name
  - **Password**: Securely hashed password for login
  - **Profile Image**: URL or file for user profile picture

### 4. Non-Functional Requirements

#### 4.1 Scalability
- The platform should handle multiple projects and users, with room to grow as more projects and users join.
- **Firebase** will provide real-time synchronization and scalability for handling dynamic data.

#### 4.2 Security
- Secure login using Spring Security.
- Role-based access control to ensure members and admins have proper permissions.

#### 4.3 Performance
- Efficient retrieval of tasks, projects, and files, ensuring a smooth user experience.

#### 4.4 Usability
- The platform should have an intuitive UI for both admins and members.
- Mobile responsiveness through the use of either **React** or **Vue** for the front-end.

#### 4.5 Availability
- The platform must be available for users at least 99.9% of the time, with regular backups in place.

### 5. Technical Stack
- **Backend**:
  - **Java** (Primary language)
  - **Spring Boot** (Framework for backend APIs)
  - **Hibernate** (For ORM and database interaction)
  - **Spring Security** (For authentication and role-based access control)

- **Frontend**:
  - **React.js** or **Vue.js** for user interface development.

- **Database**:
  - **Firebase** for real-time data storage and synchronization.

- **File Storage**:
  - Firebase for storing project documents and member files.
