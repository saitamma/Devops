dojo.provide("xwt/widget/tasknavigator/PDIWizard");
dojo.require("dijit._Templated");
dojo.require("xwt.widget.tasknavigator.TaskNavigator");
dojo.declare("xwt/widget/tasknavigator/PDIWizard", [dijit._Widget, dijit._Templated], {
	// summary:
	//		This is an example class that uses the task navigator to move between a series of tasks
	//		that are contained within a stack container.  Its purpose is to demonstrate how the TaskNavigator
	//		can be used to build composite Wizards.  This code is a good baseline for building your
	//		own wizard.  The task navigator + all pane content is configured via the 'config' parameter.
	//		This is the same data object that task navigator uses with one enhancement.  Each task is expected
	//		to have URL to where the wizard pane content is.	

	// templatString [protected] String
	//		The template to use for the container.
	//templateString: dojo.cache("xwt", "widget/tests/tasknavigator/example/templates/TNWizard.html"),
	templateString: '<div style="overflow: hidden;" class="PDIWizard"><div dojoType="dijit.layout.BorderContainer" dojoAttachPoint="borderContainer" gutters="false" style="overflow: hidden;"><div dojoType="dojox.layout.ContentPane" dojoAttachPoint="tnContainer" region="top" style="overflow: hidden; height: 18.0rem;" splitter="false"></div><div dojoType="dijit.layout.StackContainer" region="center" dojoAttachPoint="stackContainer" style="overflow: hidden;" splitter="false"></div><div dojoType="dojox.layout.ContentPane" region="bottom" dojoAttachPoint="buttonContainer" style="overflow: hidden;" splitter="false"><div class="buttonAlignment" style="padding: 1.0rem 1.8rem 0.1rem 1.0rem;text-align:right;"><button style="display:none;" dojoAttachPoint="prevButton" type="button" dojoType="xwt.widget.form.TextButton" dojoAttachEvent="onClick: _previous">${prevLabel}</button><span style="display: inline-block; width: 0.5rem;"></span><button class="finishButtonClass" style="display:none;" dojoAttachPoint="finishButton" type="button" dojoType="xwt.widget.form.TextButton" dojoAttachEvent="onClick:_finish" disabled="false">${finishLabel}</button><span style="display: inline-block;width: 0.5rem;"></span><button class="exitButtonClass" id="exitButtonForWizard" style="margin-right:2rem;" dojoAttachPoint="cancelButton" type="button" dojoType="xwt.widget.form.TextButton" dojoAttachEvent="onClick:_cancel">${cancelLabel}</button><span style="display: inline-block; width: 0.5rem;"></span><button class="nextButtonClass" style="margin-right:4rem;" id="nextButtonForWizard" dojoAttachPoint="nextButton" type="button" dojoType="xwt.widget.form.TextButton" dojoAttachEvent="onClick: _next">${nextLabel}</button></div></div></div></div>',
	
	// widgetsInTemplate [protected] boolean
	//		Flag indicating widgets are in the template and require
	//		processing.
	widgetsInTemplate: true,
	
	// config: [public] Object
	//		The configuration to use when creating the task navigator.
	config: {},
	
	//leftNavIndex takes the index number(starting from 0) of task which have sub tasks, if any, in array format. 
	leftNavIndex: {},
	
	// baseTaskTopic: [public] String
	//		This is the base topic the 'wizard' listens to for events from
	//		the tasks to do stuff like enable/disable next, task state, etc.
	//		The current widget id is always appended to this base topic.
	//		The default is:
	//		/xwt/widget/tests/tasknavigator/example/TNWizard/<widget id>
	taskTopic: "js/PDIWizard",
	
	// The following are labels used in the buttons, pulled out as properties to make
	// them translatable
	
	
	// prevLabel: [public] String
	//		The label for the previous button.  
	prevLabel: "",
	
	// nextLabel: [public] String
	//		The label for the next button.  
	nextLabel: "",
	
	// finishLabel: [public] String
	//		The label for the finish button.  
	finishLabel: "",
	
	// cancelLabel: [public] String
	//		The label for the cancel button.  
	cancelLabel: "",
	
	// _completeState: [private] Map
	//		A mapping of what tasks are complete or not.  Used to check that
	//		all tasks are complete before enabling finish.
	_completeState: {},
	

	// taskNavigatorClass: [protected] Function
	//		The constructor class for the Task Navigator.
	taskNavigatorClass: xwt.widget.tasknavigator.TaskNavigator,

	postMixInProperties: function(){
		// summary:
		//		Function for handling things to occur before
		//		template rendering, such as pulling in labels 
		//		translated labels.
		
		// This is checking that if they labels are still empty string
		// provide a value.   The value could come from an NLS bundle.
		// but for this example, they're just hardcoded to some value.
		if(!this.prevLabel){
			this.prevLabel = "Back";
		}
		if(!this.nextLabel){
			this.nextLabel = "Save And Next";
		}
		if(!this.finishLabel){
			this.finishLabel = "Finish";
		}
		if(!this.cancelLabel){
			this.cancelLabel = "Save And Exit";
		}
		this.inherited(arguments);
	},
	
	startup: function(){
		
		// summary:
		//		Over-ride of startup, to handle configuring topics, etc, to listen to
		if(!this._started){
			this.inherited(arguments);
			this._tn = new this.taskNavigatorClass(this.config);			
			
			// Drive our own _onTaskChange to swap views, etc.
			this.connect(this._tn, "onTaskChange", "_onTaskChange");
			
			// Proxy the task help events.
			this.connect(this._tn, "onTaskHelpShow","onTaskHelpShow");
			this.connect(this._tn, "onTaskHelpHide","onTaskHelpHide");
			
			this.tnContainer.domNode.appendChild(this._tn.domNode);
			this._tn.startup();

			if(this.taskTopic === "js/PDIWizard"){
				// Using generic one, so create one based on the id!.
				this.taskTopic = this.taskTopic + "/" + this.id;
			}
			this._taskTopicListeners = [];
			this._taskTopicListeners.push(dojo.subscribe(this.taskTopic, this, "_handleTaskEvent"));
			this._completeState = {};
			this._createTaskPanes();
			this._checkState();
			this.resize();
		}
	},
	
	getTasks: function(){
		// summary:
		//		Return childNodes which represent Task objects
		// tags:
		//		public
		return this.get("tasks");
	},
	
	getByTaskId: function(taskId){
		// summary:
		//		This function retrieves a task by its ID.
		//		If no task can be found, then it returns null/undefined.
		// tags:
		//		public
		return this._tn.getByTaskId(taskId);
	},

	getByTaskIndex: function(idx){
		// summary:
		//		This function retrieves a task by its index in the tasks array.
		//		If no task can be found, then it returns null/undefined.
		// tags:
		//		public
		return this._tn.getByTaskIndex(idx);
	},
	
	_getTasksAttr: function(){
		// summary:
		//		Inteal function to map the standard dijit attr function
		//		to the proper way to get your 'tasks' back.
		// tags:
		//		protected
		return this._tn.get("tasks");
	},
	
	addTask: function(widget, index){
		// summary:
		//		add new task to task navigator
		// widget: widget or task creation parameters.
		//		widget object or JavaScript object with task creation parameters.
		// index: int
		//		position where put new task
		//		widget will be put to the end if index is undefined
		// tags:
		//		public
		if(widget && (!widget.declaredClass || !widget.isInstanceOf || !widget.isInstanceOf(xwt.widget.tasknavigator.Task))){
			// We weren't passed a real task, probably just a task definition, so we need to create
			// a task.
			widget = new xwt.widget.tasknavigator.Task(widget);
			if(index && index > this._tn.get("lastEnabled")){
				widget.set("state", "disabled");
			}
		}
		if(!widget){
			console.warn("xwt.widget.tasknavigator.TaskNavigator.addTask: No value was passed in for the widget in instance: [", this.id, "].  Aborting add.");
			return;
		}else{
			// Add the widget into the task navigation, then
			// add the pane.
			this._tn.addTask(widget, index);
			this.stackContainer.addChild(new dojox.layout.ContentPane({
				href: widget.href, 
				scriptHasHooks: true, 
				taskTopic: this.taskTopic, 
				sharedData: this._sharedData
			}), index);
			this._completeState[widget.taskId] = false;
			this.onAddTask(widget);
		}
	},
	removeTask: function(widget){
		// summary:
		//		remove task from task navigator
		// widget : widget, string, int.
		//		task widget, taskId, or task index to remove.
		// tags:
		//		public
	
		var tasks = this.get("tasks");
		if(typeof widget === "string"){
			widget = this.getByTaskId(widget);
		}
		if(typeof widget === "number"){
			widget = tasks[widget];
		}
		
		if(!widget){
			// No widget to remove, just return.
			return null;
		}else{
			var idx = widget.index;
			this._tn.removeTask(widget);
			var child = this.stackContainer.getChildren()[idx];
			if(child){
				this.stackContainer.removeChild(child);
				child.destroyRecursive();
			}
			delete this._completeState[widget.taskId];
		}
		this.onTaskRemove(widget);
		return widget;
	},	
	
	destroy: function(){
		// summary:
		//		Over-ridden life-cycle function to handle cleanup of programmatic
		//		allocations, such as the topic listener.
		dojo.forEach(this._taskTopicListeners, function(l){
			dojo.unsubscribe(l);
		},this);

		// Delete our shared data object, if any.
		delete this._sharedData;

		// All widgets in template, such as the stack container
		// are cleaned up in the superclass.
		this.inherited(arguments);
	},
	
	resize: function(size){
		// summary:
		//		Implementation that passes through the resize data
		//		to the borderContainer.
		// size:
		//		The size to scale the table container to.
		// tags:
		//		protected
		if(size){
			dojo.contentBox(this.domNode, size);
		}else{
			size = dojo.contentBox(this.domNode);
		}
		if(this.borderContainer){
			this.borderContainer.resize(size);
		}
	},
	
	_createTaskPanes: function(){
		// summary:
		//		This function creates all the task panes held in the
		//		stack container.
		var tasks = this.config.tasks;
		
		// Create a shared object each task pane can pull from/put to.
		this._sharedData = {};

		var i = 0;
		dojo.forEach(tasks, function(t){
			
			// Set the initial completion state to false.	
			this._completeState[t.taskId] = false;
			i++;
				
			// Create a new wizard pane with the href defined in the task and also attach the
			// task topic for this wizard so the pane data (in scriptHasHooks), can use it to call
			// back to this wizard.  It also attaches a shared object that each task can access 
			// and do stuff with.
			var cp = new dojox.layout.ContentPane({href: t.href, scriptHasHooks: true, taskTopic: this.taskTopic, sharedData: this._sharedData, taskId: t.taskId});
			this.stackContainer.addChild(cp);
		}, this);
	},
	
	_checkState: function(){
		// summary:
		//		Function to check the state of the task and set the buttons 
		//		appropriately.
		var index = this._tn.get("selectedTask");
		var lastEnabledTask = this._tn.get("lastEnabledTask");
		var tasks = this._tn.get("tasks");
		var task = this._tn.get("tasks")[index];

		// Enable/disable the previous button.
		if(index === 0){
			this.prevButton.set("disabled", true);
		}else{
			this.prevButton.set("disabled", false);
		}
		
		if(index === tasks.length - 1 || index === lastEnabledTask){
			// Last task that's currently enabled, or the last task, so turn off
			// the next button.
			this.nextButton.set("disabled", false);
		}else{
			this.nextButton.set("disabled", false);
		}
		
		// Iterate over the task states and check for completeness, 
		// then enable/disable the finish button if they are all 
		// complete.
		var enableFinish = true;
		var i;
		for(i in this._completeState){
			if(!this._completeState[i]){
				enableFinish = false;
				break;
			}
		}
		this.finishButton.set("disabled", !enableFinish);
	},
	
	/* Template defined events used for navigation */
	_cancel: function(){
		// summary:
		//		Function called when cancel is clicked.
		// tags:
		//		protected
		this.onCancel();
	},
	
	_finish: function(){
		// summary:
		//		Function called when finish is clicked.
		// tags:
		//		protected
		this.onFinish();
	},

	onCancel: function(){
		//alert("process going to cancel");
		// summary:
		//		Function called when cancel is clicked.
		// tags:
		//		callback
		//console.log("PDIWizard.cancel: Cancel pressed.");
	},
	
	onFinish: function(){
		// summary:
		//		Function called when finish is clicked.
		// tags:
		//		callback
		console.log("PDIWizard.finish: Finish pressed.");
	},
	beforeNext: function(thiss){
		
		// present left nav define here
		
	},
	_next: function(){
		
		if(this.beforeNext(this)){
			// summary:
			//		Function called when next is clicked.
			// tags:
			//		protected
			
			// Get the current task index and then find the next 
			// task object.
			var index = this._tn.selectedTask;
			var nextTask = this._tn.get("tasks")[index + 1];
			var currentTask = this._tn.get("tasks")[index];
				
			this.publishTask(index);
			
			
			if(nextTask && !this._tn.animating){
				// Mark task completed and move to next if we're
				// not already in the middle of animating.
				this._tn.set("selectedTask", nextTask.index);
				this._setPane(nextTask.index);
			}
			this._checkState();
			
		}
	
	},
	beforePrevious: function(thisObj){
		//call this function before previous nav task
		return true;
		
	},
	_previous: function(){
		if(this.beforePrevious(this)){
			// summary:
			//		Function called when cancel is clicked.
			// tags:
			//		protected
			
			// Get the current task index and then find the previous 
			// task object.
			var index = this._tn.selectedTask;
			var nextTask = this._tn.get("tasks")[index - 1];
			if(nextTask && !this._tn.animating){
				// We have one, so shift to it if we're not already in the
				// middle of animating.
				this._tn.set("selectedTask", nextTask.index);
				this._setPane(nextTask.index);
			}
			this._checkState();
		}
	},
	
	/* End of template events */

	_setPane: function(taskIndex){
		// summary:
		//		Function to set the stack container pane to reference
		//		to the task.
		// taskIndex: int
		//		The pane index to set the page to.
		
		// Try to look up the corrisponding index
		// and select that pane
		var scp = this.stackContainer.getChildren()[taskIndex];
		if(scp){
			this.stackContainer.selectChild(scp);
		}else{
			console.warn("PDIWizard._setPane: Unable to find pane for task index: ", taskIndex, " in wizard instance: ", this.id);
		}
	},
	
	_handleTaskEvent: function(eventData){
		// summary:
		//		This function handles events published by the tasks to do things
		//		like enable/disable the next button, etc.
		// eventData: Object
		//		The event information broadcasted by a child pane.  Expected format:
		//		{
		//			taskId: id, // int id of the task acted on
		//			completed: boolean, // if the task was completed or not.
		//			enableNextTask: boolean // If the next task in the list should be enabled.
		//		}
		// tags:
		//		private
		if(eventData){
			var len = this._tn.get("tasks").length;
			var lastCompleted;
			
			// Update the task state.
			var task = this._tn.get("tasks")[this._tn.get("selectedTask")];
			if(task){
				if(eventData.completed){
					this._completeState[eventData.taskId] = true;
					task.set("progress", this._tn.PROGRESS_COMPLETED);
				}else{
					this._completeState[eventData.taskId] = false;
					task.set("progress", this._tn.PROGRESS_WORKING);
				}
			}

			if(this._tn.showArrows){
				// Set the last enabled task, based on completion states.
				var tIndex = this.getByTaskId(eventData.taskId).index;
				if(len && (len - 1) !== tIndex){
					var tId = 0;
					while(tId < len && this._completeState[this.getByTaskIndex(tId).taskId]){
						// Count to the last 'completed' task.
						lastCompleted = this.getByTaskIndex(tId).taskId;
						tId++;
					}
					if(tId == len){
						// One too many, so remove it.
						tId--;
					}
					this._tn.set("lastEnabledTask", tId);
				}else{
					this._tn.set("lastEnabledTask", this.get("tasks").length - 1);
					lastCompleted = this.getByTaskIndex(this.get("tasks").length - 1).taskId;
				}
				
				// Go ahead and update the shared data area with the last completed task
				// from our list.
				this._sharedData.lastTaskCompleted = lastCompleted;
			}
			
			// Update button states.
			this._checkState();
		}
	},
	
	_onTaskChange: function(taskId){
		// Summary:
		//		Function executed when people click on the task navigator 
		//		to change tasks.  This allows people to decide if a task 
		//		should really change or not.  This is linked up in the
		//		startup method of this widget.
		// taskId:
		//		The numeric task ID clicked on.
		// tags:
		//		protected
		
		var task = this._tn.get("tasks")[taskId];
		var pane = this.stackContainer.getChildren()[taskId];
		var currentPane = this.stackContainer.selectedChildWidget;
		if(task && pane && currentPane && pane !== currentPane){
			// It's not the current pane, so go ahead and jump everything.
			this._setPane(taskId);
			this._tn.set("selectedTask", taskId);
			this._checkState();
		}
	},
	
	onTaskHelpShow: function(task, panel){
		// summary:
		//		Function invoked when a help panel is displayed
		//		This allows users to customtize the panel contents
		//		even more directly.
		// task: [object] 
		//		The task whose panel has opened.
		// panel: [object]
		//		The help panel, which is an extension of a Popover.
	},
	
	onTaskHelpHide: function(task, panel){
		// summary:
		//		Function invoked when a help panel is displayed
		//		This allows users to customtize the panel contents
		//		even more directly.
		// task: [object] 
		//		The task whose panel has closed.
		// panel: [object]
		//		The help panel that closed, which is an extension of a Popover.
	},

	onTaskRemove: function(task){
		// summary:
		//		Callback fired when a task is removed.
		// task: Widget
		//		The task widget/instance removed.
		// tags:
		//		callback
	},
	
	onTaskAdd: function(task){
		// summary:
		//		Callback fired when a task is added.
		// task: Widget
		//		The task widget/instance added.
		// tags:
		//		callback
	},
	
	publishTask: function(index){
		// Author vishnu to publish task
		var task = this.get("tasks")[index];
		if(task){
			task.set("progress", "completed");
		}
	}
});
