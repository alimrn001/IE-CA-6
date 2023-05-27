import "../../assets/styles/product-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Baloot from "../../assets/img/Baloot.svg";
import React, { Component, useState } from "react";

const searchOptions = ["name", "category", "provider"];

class Nav extends Component {
  constructor(props) {
    super(props);

    this.state = {
      searchField: "name",
      searchValue: "",
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSearchValueChange = this.handleSearchValueChange.bind(this);
  }

  handleChange(e) {
    console.log("selecting new search field!");
    this.setState({ searchField: e.target.value }, () => {
      // console.log(this.state.searchField);
    });
  }

  handleSearchValueChange(e) {
    this.setState({ searchValue: e.target.value }, () => {
      // console.log(this.state.searchValue);
    });
  }

  render() {
    return (
      <div>
        <header>
          <nav className="navbar navbar-expand-lg navbar-light bg-white navbar-top fixed-top shadow-sm rounded">
            <div className="container-fluid justify-content-between">
              <div>
                <a className="navbar-brand" href="/">
                  <img
                    src={Baloot}
                    alt=""
                    width="55"
                    height="55"
                    className="d-inline-block align-text"
                  />
                </a>
                <a className="app-name-navbar align-middle">Baloot</a>
              </div>

              <div className="input-group search-form">
                <form
                  onSubmit={(event) =>
                    this.props.onSearch(
                      event,
                      this.state.searchField,
                      this.state.searchValue
                    )
                  }
                >
                  <div className="input-group mb-3">
                    <select
                      className="form-select search-option"
                      id="inputGroupSelect01"
                      onChange={this.handleChange}
                    >
                      {searchOptions.map((option) => (
                        <option value={option}>{option}</option>
                      ))}
                    </select>
                    <input
                      type="text"
                      className="form-control-lg search-input"
                      placeholder=" search your product ..."
                      aria-label="Text input with dropdown button"
                      value={this.state.searchValue}
                      onChange={this.handleSearchValueChange}
                    />
                  </div>
                </form>
              </div>

              <div className="d-flex">
                <ul className="navbar-nav ms-auto">
                  <li className="nav-item">
                    <a
                      className="btn btn-primary btn-register-nv"
                      href="register"
                      role="button"
                    >
                      Register
                    </a>
                  </li>
                </ul>

                <ul className="navbar-nav ">
                  <li className="nav-item">
                    <a
                      className="btn btn-primary btn-login-nv"
                      href="login"
                      role="button"
                    >
                      Login
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </header>
      </div>
    );
  }
}

export default Nav;
