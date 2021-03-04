# excellence-animator
Simple Java command line animator.
Takes in a file specifying the shapes in the animation, followed by how they change position, size, and color throughout the animation, and outputs the animation according to the selected animation type.

The program can take the following arguments:

-view [view-type]
where [view-type] is one of: interactive, visual, svg, or text

-in [animation-file]
where [animation-file] is a written explanation of an animation in the format of the text files already provided

-speed [speed-in-ticks]

-out [out-file]
where [out-file] is the name of the file to which the textual or svg views should be output to. If unspecified, outputs to stdout
