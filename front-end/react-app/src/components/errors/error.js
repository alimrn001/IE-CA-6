import "../../assets/styles/status-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Baloot from "../../assets/img/Baloot.svg";
import React, { Component } from "react";
import Nav from "./nav";
import Footer from "./footer";

class Error extends Component {
  render() {
    return (
      <div>
        <Nav />
        <div className="page-body m-0">
          <div className="d-flex align-items-center justify-content-center vh-100">
            <div className="text-center">
              <h1 className="display-1 fw-bold status-code mb-0">
                {this.props.errorCode}
              </h1>
              {this.props.errorCode === "404" && (
                <p className="fs-3 status-msg">Page not found.</p>
              )}
              {this.props.errorCode === "403" && (
                <p className="fs-3 status-msg">Forbidden.</p>
              )}
              {this.props.errorCode === "400" && (
                <p className="fs-3 status-msg">Bad request.</p>
              )}

              {this.props.errorCode === "404" && (
                <p className="lead status-msg-2">
                  The page you’re looking for doesn’t exist.
                </p>
              )}
              {this.props.errorCode === "403" && (
                <p className="lead status-msg-2">
                  You dont have permission to access this page.
                </p>
              )}
              {this.props.errorCode === "400" && (
                <p className="lead status-msg-2">
                  Your request resulted in an error.
                </p>
              )}

              <a href="/" className="btn btn-primary home-btn">
                Home
              </a>
            </div>
          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

export default Error;
