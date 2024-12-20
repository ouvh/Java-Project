import axios from '../../plugins/axios';
const state = {
  projects: []
};

const getters = {
  projects: state => state.projects
};

const actions = {
  async fetchProjects({ commit },data) {
    try {
      const userresponse = await axios.get('/api/users/'+data);
      console.log(data)
    
        const projectsIds = userresponse.data.joinedProjectsIds;

        const projects = await Promise.all(
            projectsIds.map(async (id) => {
              const response = await axios.get(`/api/projects/${id}`);
              return response.data; // Assuming the API returns project details
            })
          );


      commit('setProjects', projects);
    } catch (error) {
      console.error('Error fetching projects:', error);
    }
  },
  async createProject({ commit }, projectData) {
      try {
      const response = await axios.post("/api/projects/create?adminId="+projectData.adminIDD, projectData);
      commit('addProject', response.data);
    } catch (error) {
      console.error('Error creating project:', error);
    }
  }
};

const mutations = {
  setProjects(state, projects) {
    state.projects = projects;
  },
  addProject(state, project) {
    state.projects.push(project);
  }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
