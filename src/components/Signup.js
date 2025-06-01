import React, { useEffect, useState } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import { Link, useNavigate } from "react-router-dom"; // 👈 Add useNavigate
import "react-toastify/dist/ReactToastify.css";

// Icons and styles
import userIcon from "../img/user.svg";
import emailIcon from "../img/email.svg";
import passwordIcon from "../img/password.svg";
import styles from "./Signup.module.css";

const SignUp = () => {
  const navigate = useNavigate(); // 👈 Hook for navigation

  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
    phone_no: "",
    complete_address: "",
    district: "",
    state: "",
    country: "",
    pincode: "",
    isAccepted: false,
  });

  const [errors, setErrors] = useState({});
  const [touched, setTouched] = useState({});

  useEffect(() => {
    const errs = {};
    if (!data.name) errs.name = "Name is required";
    if (!data.email.includes("@")) errs.email = "Invalid email";
    if (!data.password || data.password.length < 6) errs.password = "Password too short";
    if (data.password !== data.confirmPassword) errs.confirmPassword = "Passwords do not match";
    if (!data.phone_no) errs.phone_no = "Phone number required";
    if (!data.complete_address) errs.complete_address = "Address is required";
    if (!data.district) errs.district = "District required";
    if (!data.state) errs.state = "State required";
    if (!data.country) errs.country = "Country required";
    if (!data.pincode) errs.pincode = "Pincode required";
    if (!data.isAccepted) errs.isAccepted = "You must accept terms";
    setErrors(errs);
  }, [data]);

  const changeHandler = (e) => {
    const { name, value, type, checked } = e.target;
    setData({ ...data, [name]: type === "checkbox" ? checked : value });
  };

  const focusHandler = (e) => {
    setTouched({ ...touched, [e.target.name]: true });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    if (Object.keys(errors).length === 0) {
      try {
        const payload = {
          ...data,
          role: "cd6e947d-583c-4bed-85a6-fb25523ba8d1", // Hardcoded admin role UUID
        };

        await axios.post("http://localhost:8080/api/v1/user/register", payload, {
          headers: { "Content-Type": "application/json" },
        });

        toast.success("Registered successfully!");

        setTimeout(() => {
          navigate("/login"); // 👈 Redirect to login after success
        }, 1500); // Optional: delay so user sees toast

      } catch (err) {
        console.error(err);
        toast.error("Registration failed. Check server.");
      }
    } else {
      toast.error("Please fix the form errors");
      setTouched(Object.fromEntries(Object.keys(data).map((key) => [key, true])));
    }
  };

  return (
    <div className={styles.container}>
      <form className={styles.formLogin} onSubmit={submitHandler} autoComplete="off">
        <h2>Sign Up</h2>

        {["name", "email", "phone_no", "password", "confirmPassword", "complete_address", "district", "state", "country", "pincode"].map((field) => (
          <div key={field}>
            <div className={errors[field] && touched[field] ? styles.unCompleted : touched[field] ? styles.completed : ""}>
              <input
                type={field.toLowerCase().includes("password") ? "password" : "text"}
                name={field}
                value={data[field]}
                placeholder={field.replace("_", " ").replace(/^\w/, (c) => c.toUpperCase())}
                onChange={changeHandler}
                onFocus={focusHandler}
              />
              <img
                src={
                  field.includes("password")
                    ? passwordIcon
                    : field.includes("email")
                    ? emailIcon
                    : userIcon
                }
                alt=""
              />
            </div>
            {errors[field] && touched[field] && <span className={styles.error}>{errors[field]}</span>}
          </div>
        ))}

        <div className={styles.terms}>
          <input
            type="checkbox"
            name="isAccepted"
            checked={data.isAccepted}
            onChange={changeHandler}
            onFocus={focusHandler}
          />
          <label>I accept the terms of privacy policy</label>
          {errors.isAccepted && touched.isAccepted && (
            <span className={styles.error}>{errors.isAccepted}</span>
          )}
        </div>

        <button type="submit">Create Account</button>
        <span>
          Already have an account? <Link to="/login">Sign In</Link>
        </span>
      </form>
      <ToastContainer />
    </div>
  );
};

export default SignUp;
