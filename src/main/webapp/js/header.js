var headerItems = {
		items : [{
	        actionId: 'user',
	        label: '',
	        username: userCec.toUpperCase()+' - Settings ',
	        type: 'header'
	    },
	    {
			actionId:'aboutTarget',
			href:'javascript:about()',
			label:'About',
			target:"_self"
		}, 
		/*{
			id : "domain",
			actionId : 'domain',
			label : 'Reset License',
			href : 'javascript:reset()',
			target : "_self"
		},
		*/
		  {
			id : "help",
			actionId : 'help',
			href : 'help/index.htm',
			label : 'Help',
			target : '_blank'
		},
		{
			id : "logout",
			actionId : 'logout',
			href : "logout.html",
			//href : 'j_spring_security_logout',
			label : 'Logout',
			target : '_self'
		} ]
	};

if(userRole == "product_owner" || userRole == "super_admin" || userRole == "admin"){
	headerItems.items.splice(1, 0 , {
			id : "usersmgmt",
			actionId : 'usersmgmt',
			href : 'userManagement.html',
			label : 'Users Mgmt',
		}
	);
}
