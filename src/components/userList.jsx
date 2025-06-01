import React, { useEffect, useState } from "react";
import { getAllUsers } from "../api/userService";





function UserList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getAllUsers()
      .then((res) => setUsers(res.data))
      .catch((err) => console.error("Failed to fetch users", err));
  }, []);

  return (
    <div>
      <h2>User List</h2>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            {user.name} | {user.email} | {user.phone_no}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default UserList;
