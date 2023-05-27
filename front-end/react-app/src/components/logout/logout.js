import axios from "../../api/axios";
import React, { Component } from "react";
// import { createBrowserRouter, RouterProvider } from "react-router-dom";

class Logout extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    axios
      .post("/logout")
      .then((resp) => {
        if (resp.status === 200) {
          window.location.href = "http://localhost:3000/login";
        }
      })
      .catch((error) => {});
  }

  render() {
    return <h1></h1>;
  }
}

export default Logout;
