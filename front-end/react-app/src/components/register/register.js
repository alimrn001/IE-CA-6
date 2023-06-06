import React, { Component } from "react";
import Nav from "./nav";
import Footer from "./footer";
import "../../assets/styles/register-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "../../api/axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

// import { createBrowserRouter, RouterProvider } from "react-router-dom";

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      email: "",
      birthday: "",
      address: "",
    };
    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handleBirthdayChange = this.handleBirthdayChange.bind(this);
    this.handleAddressChange = this.handleAddressChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleUsernameChange(event) {
    this.setState({
      username: event.target.value,
    });
  }

  handlePasswordChange(event) {
    this.setState({
      password: event.target.value,
    });
  }

  handleEmailChange(event) {
    this.setState({
      email: event.target.value,
    });
  }

  handleBirthdayChange(event) {
    this.setState({
      birthday: event.target.value,
    });
  }

  handleAddressChange(event) {
    this.setState({
      address: event.target.value,
    });
  }

  handleSubmit(event) {
    event.preventDefault();

    axios
      .post("/register", {
        username: this.state.username,
        password: this.state.password,
        email: this.state.email,
        birthday: this.state.birthday,
        address: this.state.address,
      })
      .then((resp) => {
        if (resp.status === 200) {
          console.log("successfull");
          window.location.href = "http://localhost:3000/";
        }
      })
      .catch((error) => {
        console.log(error.status);
        if (error.response.status === 403) {
          toast.error("User with this username already exists");
        } else {
          window.location.href = "http://localhost:3000/badrequest";
        }
      });
  }

  componentDidMount() {
    document.title = "Register";
    document.body.classList.add("body-bg");
    toast.configure({
      rtl: true,
      className: "text-center",
      position: toast.POSITION.TOP_CENTER,
      autoClose: 5000,
      closeOnClick: true,
    });
  }

  render() {
    return (
      <div className="bg-light">
        <Nav />
        <div className="page-body">
          <div className="form">
            <h2>Register in Baloot</h2>
            <p class="welcome-msg">
              Complete the form below to register in Baloot!
            </p>

            <form
              className="register-from"
              action=""
              method="POST"
              onSubmit={this.handleSubmit}
            >
              {/* <div className="form-group">
                <label>First name</label>
                <input
                  name="firstName"
                  type="text"
                  className="form-control form-field"
                  onChange={this.handlePasswordChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Last name</label>
                <input
                  name="lastname"
                  type="text"
                  className="form-control form-field"
                  onChange={this.handlePasswordChange}
                  required
                />
              </div> */}

              <div className="form-group">
                <label>Username</label>
                <input
                  name="username"
                  type="text"
                  className="form-control form-field"
                  onChange={this.handleUsernameChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Password</label>
                <input
                  name="password"
                  type="password"
                  className="form-control form-field"
                  onChange={this.handlePasswordChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Email</label>
                <input
                  name="email"
                  type="email"
                  className="form-control form-field"
                  onChange={this.handleEmailChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Birthday</label>
                <input
                  name="birthday"
                  type="date"
                  className="form-control form-field"
                  onChange={this.handleBirthdayChange}
                  required
                />
              </div>

              <div className="form-group">
                <label>Address</label>
                <input
                  name="address"
                  type="text"
                  className="form-control form-field"
                  onChange={this.handleAddressChange}
                  required
                />
              </div>

              <button type="submit" className="btn btn-default login-btn">
                Register
              </button>
            </form>

            <div className="reg-link">
              <a className="reg-msg" href="http://localhost:3000/login">
                <b>Already have account? Login</b>
              </a>
            </div>
          </div>
        </div>
        {/* <Footer /> */}
      </div>
    );
  }
}

export default Register;
