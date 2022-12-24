import React from "react";

import { Icon } from "@chakra-ui/react";
import {
  MdTrendingUp
} from "react-icons/md";

// Admin Imports
import MainDashboard from "views/admin/default";
import MainDashboard1 from "views/admin/default1";
import MainDashboard2 from "views/admin/default2";
import MainDashboard3 from "views/admin/default3";
import MainDashboard4 from "views/admin/default4";
import MainDashboard5 from "views/admin/default5";
import MainDashboard6 from "views/admin/default6";
import MainDashboard7 from "views/admin/default7";
import MainDashboard8 from "views/admin/default8";

const routes = [
  {
    name: "NCM Trend",
    layout: "/admin",
    path: "/default",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard,
  },
  {
    name: "PCLP Trend",
    layout: "/admin",
    path: "/CAOTrend",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard1
  },
  {
    name: "CRP Trend",
    layout: "/admin",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    path: "/COSTrend",
    component: MainDashboard2,
  },
  {
    name: "ACV Trend",
    layout: "/admin",
    path: "/PLPTrend",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard3,
  },
  {
    name: "PP Trend",
    layout: "/admin",
    path: "/PPTrend",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard4,
  },
  {
    name: "CS Trend",
    layout: "/admin",
    path: "/CSTrend",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard5,
  },
  {
    name: "NS Trend",
    layout: "/admin",
    path: "/NSTrend",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard6,
  },
  {
    name: "NW Trend",
    layout: "/admin",
    path: "/NWrend",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard7,
  },
  {
    name: "LBS Trend",
    layout: "/admin",
    path: "/LBSrend",
    icon: <Icon as={MdTrendingUp} width='20px' height='20px' color='inherit' />,
    component: MainDashboard8,
  },
  
];

export default routes;
