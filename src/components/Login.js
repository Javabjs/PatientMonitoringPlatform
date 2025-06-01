import React, { useState } from "react";
import emailIcon from "../img/email.svg";
import passwordIcon from "../img/password.svg";
import styles from "./Signup.module.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const navigate = useNavigate();
  const [data, setData] = useState({ email: "", password: "" });
  const [touched, setTouched] = useState({});

  const changeHandler = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const focusHandler = (e) => {
    setTouched({ ...touched, [e.target.name]: true });
  };

  const validateForm = () => {
    const errors = {};
    const email = data.email || "";
    const password = data.password || "";

    if (!email.includes("@")) {
      errors.email = "Valid email is required";
    }
    if (password.length < 6) {
      errors.password = "Password must be at least 6 characters";
    }
    return errors;
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    const formErrors = validateForm();
    if (Object.keys(formErrors).length > 0) {
      setTouched({ email: true, password: true });
      toast.error("Please correct the errors before submitting.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/user/login",
        {
          email: (data.email || "").toLowerCase(),
          password: data.password || "",
        },
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      const resData = response.data;

      // ✅ Use jwtToken instead of token
      if (resData.jwtToken) {
        localStorage.setItem("token", resData.jwtToken);
        toast.success("Logged in successfully!");
        setTimeout(() => navigate("/dashboard"), 1500);
      } else {
        toast.error("Login failed: No token received.");
      }
    } catch (err) {
      console.error("Login error:", err);
      toast.error(
        err.response?.data?.message || "Invalid credentials or server error."
      );
    }
  };

  return (
    <div className={styles.container}>
      <form
        className={styles.formLogin}
        onSubmit={submitHandler}
        autoComplete="off"
      >
        <h2>Sign In</h2>

        <div>
          <div
            className={
              touched.email && !(data.email || "").includes("@")
                ? styles.unCompleted
                : ""
            }
          >
            <input
              type="text"
              name="email"
              value={data.email}
              placeholder="E-mail"
              onChange={changeHandler}
              onFocus={focusHandler}
              autoComplete="off"
            />
            <img src={emailIcon} alt="email icon" />
          </div>
        </div>

        <div>
          <div
            className={
              touched.password && (data.password || "").length < 6
                ? styles.unCompleted
                : ""
            }
          >
            <input
              type="password"
              name="password"
              value={data.password}
              placeholder="Password"
              onChange={changeHandler}
              onFocus={focusHandler}
              autoComplete="off"
            />
            <img src={passwordIcon} alt="password icon" />
          </div>
        </div>

        <div>
          <button type="submit">Login</button>
          <span className={styles.redirectText}>
            Don't have an account? <Link to="/signup">Create account</Link>
          </span>
        </div>
      </form>
      <ToastContainer />
    </div>
  );
};

export default Login;
