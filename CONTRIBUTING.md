# Welcome to the Kryptonite Contributor Guide!
Thanks for contributing to Kryptonite! We really appreciate you helping us out.

# What can I do?
We usually have a full list of tasks scheduled for the next update in our [milestones page](https://github.com/LewMC/Kryptonite/milestones).
If this is empty, you can also take a look at our [issues](https://github.com/LewMC/Kryptonite/issues), or add anything else you think would be a good fit!

## Testing
Please ensure you test all your additions in as many ways as you can.
All of our automated tests must pass, and we ask that you go in-game to check they work too.
The way we do it is we try to break it.
Try to make it do things it shouldn't.
That's usually a good indicator to if it works, but every feature is different and you should test it how you see fit.

# Where to Merge
Please merge any changes into the `next-update` branch, not the `main` branch.

It means that:
- We can check compatability with any other upcoming changes, and allows us to track development in one place.
- Our snapshot builds are labelled as next-update so that it is clear to users download them that they are still in development, and that any changes being made will work with future versions of Essence.
- The `main` branch can continue to be used by those who are looking for a tested, stable version of Kryptonite, whilst our `next-update` branch is for development.

We'll merge `next-update` into `main` when the next update is released, until then - please merge any changes into `next-update`!
