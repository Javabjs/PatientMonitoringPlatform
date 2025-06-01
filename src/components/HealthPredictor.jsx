import React, { useState } from "react";

const HealthPredictor = () => {
  const [formData, setFormData] = useState({
    heart_rate: "",
    systolic_bp: "",
    diastolic_bp: "",
    cholesterol: "",     // ✅ added missing field
    blood_sugar: "",
  });

  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setResult(null);

    try {
      const response = await fetch("http://127.0.0.1:5000/predict", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          heart_rate: Number(formData.heart_rate),
          systolic_bp: Number(formData.systolic_bp),
          diastolic_bp: Number(formData.diastolic_bp),
          cholesterol: Number(formData.cholesterol),
          blood_sugar: Number(formData.blood_sugar),
        }),
      });

      const data = await response.json();

      if (response.ok) {
        setResult(data.condition);
      } else {
        setError(data.error || "Unknown error");
      }
    } catch (err) {
      setError("Failed to fetch prediction");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "auto", padding: 20 }}>
      <h2>Health Condition Predictor</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Heart Rate (bpm):
          <input
            type="number"
            name="heart_rate"
            value={formData.heart_rate}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Systolic BP (mmHg):
          <input
            type="number"
            name="systolic_bp"
            value={formData.systolic_bp}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Diastolic BP (mmHg):
          <input
            type="number"
            name="diastolic_bp"
            value={formData.diastolic_bp}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Cholesterol (mg/dL):
          <input
            type="number"
            name="cholesterol"
            value={formData.cholesterol}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Blood Sugar (mg/dL):
          <input
            type="number"
            name="blood_sugar"
            value={formData.blood_sugar}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <button type="submit" disabled={loading}>
          {loading ? "Predicting..." : "Predict"}
        </button>
      </form>

      {result && (
        <div style={{ marginTop: 20, fontWeight: "bold" }}>
          Predicted Condition: {result}
        </div>
      )}

      {error && (
        <div style={{ marginTop: 20, color: "red" }}>Error: {error}</div>
      )}
    </div>
  );
};

export default HealthPredictor;
